trait HTTPVersionInfo {
	def since: HTTPVersion
}

abstract class HTTPElement extends HTTPVersionInfo

case class HTTPStatus(val code: java.lang.Integer) extends HTTPElement {
	val messages = Map (
		100 ->  ("Continued", HTTPVersion("1.1")),
		101 ->  ("Switching Protocols", HTTPVersion("1.1")),
		200 ->  "OK",
		201 ->  "Created",
		202 ->  "Accepted",
		203 ->  ("Non-Authoritative Information", HTTPVersion("1.1")),
		204 ->  "No Content",
		205 ->  ("Reset Content", HTTPVersion("1.1")),
		206 ->  ("Partial Content", HTTPVersion("1.1")),
		300 ->  "Multiple Choices",
		301 ->  "Moved Permanently",
		302 ->  "Found",
		303 ->  ("See Other", HTTPVersion("1.1")),
		304 ->  "Not Modified",
		305 ->  ("Use Proxy", HTTPVersion("1.1")),
		307 ->  ("Temporary Redirect", HTTPVersion("1.1")),
		400 ->  "Bad Request",
		401 ->  "Unauthorized",
		402 ->  ("Payment Required", HTTPVersion("1.1")),
		403 ->  "Forbidden",
		404 ->  "Not Found",
		405 ->  ("Method Not Allowed", HTTPVersion("1.1")),
		406 ->  ("Not Acceptable", HTTPVersion("1.1")),
		407 ->  ("Proxy Authentication Required", HTTPVersion("1.1")),
		408 ->  ("Request Timeout", HTTPVersion("1.1")),
		409 ->  ("Conflict", HTTPVersion("1.1")),
		410 ->  ("Gone", HTTPVersion("1.1")),
		411 ->  ("Length Required", HTTPVersion("1.1")),
		412 ->  ("Precondition Failed", HTTPVersion("1.1")),
		413 ->  ("Request Entity Too Large", HTTPVersion("1.1")),
		414 ->  ("Request-URI Too Long", HTTPVersion("1.1")),
		415 ->  ("Unsupported Media Type", HTTPVersion("1.1")),
		416 ->  ("Requested Range Not Satisfiable", HTTPVersion("1.1")),
		417 ->  ("Expectation Failed", HTTPVersion("1.1")),
		500 ->  "Internal Server Error",
		501 ->  "Not Implemented",
		502 ->  "Bad Gateway",
		503 ->  "Service Unavailable",
		504 ->  ("Gateway Timeout", HTTPVersion("1.1")),
		505 ->  ("HTTP Version Not Supported", HTTPVersion("1.1"))
		);
	def message =
		messages(code) match {
			case Tuple2(msg, _) => msg
			case msg: String => msg
		}
	def since: HTTPVersion =
		messages(code) match {
			case Tuple2(_, v: HTTPVersion) => v
			case _ => HTTPVersion("1.0")
		}
	override def toString = code + " " + message
}

case class HTTPVersion(val version: String) {
	def >  (v: HTTPVersion) = v.version <  version
	def <  (v: HTTPVersion) = v.version >  version
	def <= (v: HTTPVersion) = v.version <= version
	def >= (v: HTTPVersion) = v.version >= version

	override
	def toString = "HTTP/" + version;

	def supports(element: HTTPVersionInfo) = element.since >= this
}

abstract class HTTPElementEnumeration extends Enumeration {
	class ValueSince(name: String, val v: HTTPVersion) extends Val(nextId, name) with HTTPVersionInfo {
		def since: HTTPVersion = v
	} 
	def is(name: String): ValueSince = new ValueSince(name, HTTPVersion("1.0"))
	def is(name: String, since: HTTPVersion): ValueSince = new ValueSince(name, since)
}

object HTTPMethod extends HTTPElementEnumeration {
	val Connect 	= is("CONNECT", HTTPVersion("1.1"))
	val Delete 	= is("DELETE", HTTPVersion("1.1"))
	val Get		= is("GET")
	val Head 	= is("HEAD")
	val Options	= is("OPTIONS", HTTPVersion("1.1"))
	val Post	= is("POST")
	val Put		= is("PUT", HTTPVersion("1.1"))
	val Trace	= is("TRACE", HTTPVersion("1.1"))
}

object HTTPRequestHeader extends HTTPElementEnumeration {
	val Accept		= is("Accept")
	val AcceptCharset	= is("Accept-Charset")
	val AcceptDatetime	= is("Accept-Datetime")
	val AcceptEncoding	= is("Accept-Encoding")
	val AcceptLanguage	= is("Accept-Language")
	val Authorization	= is("Authorization")
	val CacheControl	= is("Cache-Control", HTTPVersion("1.1"))
	val Connection		= is("Connection", HTTPVersion("1.1"))
	val ContentLength	= is("Content-Length")
	val ContentMD5		= is("Content-MD5")
	val ContentType		= is("Content-Type")
	val Cookie		= is("Cookie")
	val Date		= is("Date")
	val Expect		= is("Expect", HTTPVersion("1.1"))
	val From		= is("From")
	val Host		= is("Host", HTTPVersion("1.1"))
	val IfMatch		= is("If-Match", HTTPVersion("1.1"))
	val IfModifiedSince	= is("If-Modified-Since")
	val IfNoneMatch		= is("If-None-Match", HTTPVersion("1.1"))
	val IfRange		= is("If-Range", HTTPVersion("1.1"))
	val IfUnmodifiedSince	= is("If-Unmodified-Since", HTTPVersion("1.1"))
	val MaxForwards		= is("Max-Forwards")
	val Pragma		= is("Pragma")
	val ProxyAuthorization	= is("Proxy-Authorization", HTTPVersion("1.1"))
	val Range		= is("Range", HTTPVersion("1.1"))
	val Referer		= is("Referer")
	val TE			= is("TE", HTTPVersion("1.1"))
	val Upgrade		= is("Upgrade", HTTPVersion("1.1"))
	val UserAgent		= is("User-Agent")
	val Via			= is("Via")
	val Warning		= is("Warning", HTTPVersion("1.1"))
}

object HTTPResponseHeader extends HTTPElementEnumeration {
	var AcceptRanges		= is("Accept-Ranges")
	var Age				= is("Age", HTTPVersion("1.1"))
	var Allow			= is("Allow")
	var CacheControl		= is("Cache-Control")
	var Connection			= is("Connection", HTTPVersion("1.1"))
	var ContentEncoding		= is("Content-Encoding")
	var ContentLanguage		= is("Content-Language")
	var ContentLength		= is("Content-Length")
	var ContentLocation		= is("Content-Location")
	var ContentMD5			= is("Content-MD5")
	var ContentDisposition		= is("Content-Disposition")
	var ContentRange		= is("Content-Range", HTTPVersion("1.1"))
	var ContentType			= is("Content-Type")
	var Date			= is("Date")
	var ETag			= is("ETag")
	var Expires			= is("Expires")
	var LastModified		= is("Last-Modified")
	var Link			= is("Link")
	var Location			= is("Location")
	var P3P				= is("P3P")
	var Pragma			= is("Pragma")
	var ProxyAuthenticate		= is("Proxy-Authenticate", HTTPVersion("1.1"))
	var Refresh			= is("Refresh")
	var RetryAfter			= is("Retry-After")
	var Server			= is("Server")
	var SetCookie			= is("Set-Cookie")
	var StrictTransportSecurity	= is("Strict-Transport-Security")
	var Trailer			= is("Trailer", HTTPVersion("1.1"))
	var TransferEncoding		= is("Transfer-Encoding", HTTPVersion("1.1"))
	var Vary			= is("Vary", HTTPVersion("1.1"))
	var Via				= is("Via", HTTPVersion("1.1"))
	var Warning			= is("Warning")
	var WWWAuthenticate		= is("WWW-Authenticate")
}

