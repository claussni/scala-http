case class HTTPStatus(val code: java.lang.Integer) {
	val messages = Map (
		100 ->  "Continued",
		101 ->  "Switching Protocols",
		102 ->  "Processing",
		200 ->  "OK",
		201 ->  "Created",
		202 ->  "Accepted",
		203 ->  "Non-Authoritative Information",
		204 ->  "No Content",
		205 ->  "Reset Content",
		206 ->  "Partial Content",
		207 ->  "Multi-Status",
		208 ->  "Already Reported",
		226 ->  "IM Used",
		300 ->  "Multiple Choices",
		301 ->  "Moved Permanently",
		302 ->  "Found",
		303 ->  "See Other",
		304 ->  "Not Modified",
		305 ->  "Use Proxy",
		306 ->  "Reserved",
		307 ->  "Temporary Redirect",
		308 ->  "Permanent Redirect",
		400 ->  "Bad Request",
		401 ->  "Unauthorized",
		402 ->  "Payment Required",
		403 ->  "Forbidden",
		404 ->  "Not Found",
		405 ->  "Method Not Allowed",
		406 ->  "Not Acceptable",
		407 ->  "Proxy Authentication Required",
		408 ->  "Request Timeout",
		409 ->  "Conflict",
		410 ->  "Gone",
		411 ->  "Length Required",
		412 ->  "Precondition Failed",
		413 ->  "Request Entity Too Large",
		414 ->  "Request-URI Too Long",
		415 ->  "Unsupported Media Type",
		416 ->  "Requested Range Not Satisfiable",
		417 ->  "Expectation Failed",
		422 ->  "Unprocessable Entity",
		423 ->  "Locked",
		424 ->  "Failed Dependency",
		426 ->  "Upgrade Required",
		427 ->  "Unassigned",
		428 ->  "Precondition Required",
		429 ->  "Too Many Requests",
		430 ->  "Unassigned",
		431 ->  "Request Header Fields Too Large",
		500 ->  "Internal Server Error",
		501 ->  "Not Implemented",
		502 ->  "Bad Gateway",
		503 ->  "Service Unavailable",
		504 ->  "Gateway Timeout",
		505 ->  "HTTP Version Not Supported",
		506 ->  "Variant Also Negotiates (Experimental)",
		507 ->  "Insufficient Storage",
		508 ->  "Loop Detected",
		509 ->  "Unassigned",
		510 ->  "Not Extended",
		511 ->  "Network Authentication Required"
		);
	override def toString = code + " " + messages(code)
}

object HTTPVersion extends Enumeration {
	type HTTPVersion = Value
	val HTTP10 = Value("HTTP 1.0")
	val HTTP11 = Value("HTTP 1.1") 
}

object HTTPMethod extends Enumeration {
	type HTTPMethod = Value
	val Connect 	= Value("Connect")
	val Delete 	= Value("Delete")
	val Get		= Value("Get")
	val Head 	= Value("Head")
	val Options	= Value("Options")
	val Post	= Value("Post")
	val Put		= Value("Put")
	val Trace	= Value("Trace")
}

abstract class HTTPHeader extends Enumeration {
	type HTTPHeader = Value
}

object HTTPRequestHeader extends HTTPHeader {
	val Accept		= Value("Accept")
	val AcceptCharset	= Value("Accept-Charset")
	val AcceptDatetime	= Value("Accept-Datetime")
	val AcceptEncoding	= Value("Accept-Encoding")
	val AcceptLanguage	= Value("Accept-Language")
	val Authorization	= Value("Authorization")
	val CacheControl	= Value("Cache-Control")
	val Connection		= Value("Connection")
	val ContentLength	= Value("Content-Length")
	val ContentMD5		= Value("Content-MD5")
	val ContentType		= Value("Content-Type")
	val Cookie		= Value("Cookie")
	val Date		= Value("Date")
	val Expect		= Value("Expect")
	val From		= Value("From")
	val Host		= Value("Host")
	val IfMatch		= Value("If-Match")
	val IfModifiedSince	= Value("If-Modified-Since")
	val IfNoneMatch		= Value("If-None-Match")
	val IfRange		= Value("If-Range")
	val IfUnmodifiedSince	= Value("If-Unmodified-Since")
	val MaxForwards		= Value("Max-Forwards")
	val Pragma		= Value("Pragma")
	val ProxyAuthorization	= Value("Proxy-Authorization")
	val Range		= Value("Range")
	val Referer		= Value("Referer")
	val TE			= Value("TE")
	val Upgrade		= Value("Upgrade")
	val UserAgent		= Value("User-Agent")
	val Via			= Value("Via")
	val Warning		= Value("Warning")
}

/**
 * Response Headers

Accept-Ranges                    
Age                              
Allow                            
Cache-Control                    
Connection                       
Content-Encoding                 
Content-Language                 
Content-Length                   
Content-Location                 
Content-MD5                      
Content-Disposition
Content-Range                    
Content-Type                     
Date                             
ETag                             
Expires                          
Last-Modified                    
Link                             
Location                         
P3P                              
Pragma                           
Proxy-Authenticate               
Refresh                          
Retry-After                      
Server                           
Set-Cookie                       
Strict-Transport-Security        
Trailer                          
Transfer-Encoding                
Vary                             
Via                              
Warning                          
WWW-Authenticate

*/