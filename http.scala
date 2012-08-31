case class HTTPStatus(val code: java.lang.Integer) {
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
			case Tuple2(_, since: HTTPVersion) => since
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

	def supports(element: Any) = 
		element match {
			case vs: HTTPElement#ValueSince => { vs.since >= this }
			case st: HTTPStatus => { st.since >= this }
			case _ => true
		}
}

abstract class HTTPElement extends Enumeration {
	class ValueSince(name: String, val since: HTTPVersion) extends Val(nextId, name)
	protected final def Value(name: String, since: HTTPVersion): ValueSince = new ValueSince(name, since)
}

object HTTPMethod extends HTTPElement {
	val Connect 	= Value("CONNECT", HTTPVersion("1.1"))
	val Delete 	= Value("DELETE", HTTPVersion("1.1"))
	val Get		= Value("GET")
	val Head 	= Value("HEAD")
	val Options	= Value("OPTIONS", HTTPVersion("1.1"))
	val Post	= Value("POST")
	val Put		= Value("PUT", HTTPVersion("1.1"))
	val Trace	= Value("TRACE", HTTPVersion("1.1"))
}

object HTTPRequestHeader extends HTTPElement {
	val Accept		= Value("Accept")
	val AcceptCharset	= Value("Accept-Charset")
	val AcceptDatetime	= Value("Accept-Datetime")
	val AcceptEncoding	= Value("Accept-Encoding")
	val AcceptLanguage	= Value("Accept-Language")
	val Authorization	= Value("Authorization")
	val CacheControl	= Value("Cache-Control", HTTPVersion("1.1"))
	val Connection		= Value("Connection", HTTPVersion("1.1"))
	val ContentLength	= Value("Content-Length")
	val ContentMD5		= Value("Content-MD5")
	val ContentType		= Value("Content-Type")
	val Cookie		= Value("Cookie")
	val Date		= Value("Date")
	val Expect		= Value("Expect", HTTPVersion("1.1"))
	val From		= Value("From")
	val Host		= Value("Host", HTTPVersion("1.1"))
	val IfMatch		= Value("If-Match", HTTPVersion("1.1"))
	val IfModifiedSince	= Value("If-Modified-Since")
	val IfNoneMatch		= Value("If-None-Match", HTTPVersion("1.1"))
	val IfRange		= Value("If-Range", HTTPVersion("1.1"))
	val IfUnmodifiedSince	= Value("If-Unmodified-Since", HTTPVersion("1.1"))
	val MaxForwards		= Value("Max-Forwards")
	val Pragma		= Value("Pragma")
	val ProxyAuthorization	= Value("Proxy-Authorization", HTTPVersion("1.1"))
	val Range		= Value("Range", HTTPVersion("1.1"))
	val Referer		= Value("Referer")
	val TE			= Value("TE", HTTPVersion("1.1"))
	val Upgrade		= Value("Upgrade", HTTPVersion("1.1"))
	val UserAgent		= Value("User-Agent")
	val Via			= Value("Via")
	val Warning		= Value("Warning", HTTPVersion("1.1"))
}

object HTTPResponseHeader extends HTTPElement {
	var AcceptRanges		= Value("Accept-Ranges")
	var Age				= Value("Age", HTTPVersion("1.1"))
	var Allow			= Value("Allow")
	var CacheControl		= Value("Cache-Control")
	var Connection			= Value("Connection", HTTPVersion("1.1"))
	var ContentEncoding		= Value("Content-Encoding")
	var ContentLanguage		= Value("Content-Language")
	var ContentLength		= Value("Content-Length")
	var ContentLocation		= Value("Content-Location")
	var ContentMD5			= Value("Content-MD5")
	var ContentDisposition		= Value("Content-Disposition")
	var ContentRange		= Value("Content-Range", HTTPVersion("1.1"))
	var ContentType			= Value("Content-Type")
	var Date			= Value("Date")
	var ETag			= Value("ETag")
	var Expires			= Value("Expires")
	var LastModified		= Value("Last-Modified")
	var Link			= Value("Link")
	var Location			= Value("Location")
	var P3P				= Value("P3P")
	var Pragma			= Value("Pragma")
	var ProxyAuthenticate		= Value("Proxy-Authenticate", HTTPVersion("1.1"))
	var Refresh			= Value("Refresh")
	var RetryAfter			= Value("Retry-After")
	var Server			= Value("Server")
	var SetCookie			= Value("Set-Cookie")
	var StrictTransportSecurity	= Value("Strict-Transport-Security")
	var Trailer			= Value("Trailer", HTTPVersion("1.1"))
	var TransferEncoding		= Value("Transfer-Encoding", HTTPVersion("1.1"))
	var Vary			= Value("Vary", HTTPVersion("1.1"))
	var Via				= Value("Via", HTTPVersion("1.1"))
	var Warning			= Value("Warning")
	var WWWAuthenticate		= Value("WWW-Authenticate")
}

