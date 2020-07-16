import com.oscarg798.lomeno.event.LogSource
import com.oscarg798.lomeno.interceptor.NetworkLoggerInterceptor
import com.oscarg798.lomeno.interceptor.requestprocessor.RequestProcessor
import com.oscarg798.lomeno.logger.Logger
import com.oscarg798.lomeno.mapper.NetworkLogEventMapperImpl
import com.oscarg798.lomeno.sources.NetworkLogSource
import io.mockk.Called
import io.mockk.every
import io.mockk.verify
import okhttp3.*
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test
import java.net.URI

class NetworkLoggerInterceptorTest {

    private val networkLogEventMapper = NetworkLogEventMapperImpl()
    private val logger = relaxedMockk<Logger>()
    private val chain = relaxedMockk<Interceptor.Chain>()
    private val request = relaxedMockk<Request>()
    private val response = relaxedMockk<Response>()
    private val url = relaxedMockk<HttpUrl>()
    private val urlUri = relaxedMockk<URI>()
    private val headers = HEADERS
    private val requestProcessor = relaxedMockk<RequestProcessor>()

    private lateinit var interceptor: NetworkLoggerInterceptor

    @Before
    fun setup() {
        every { chain.request() } answers { request }
        every { chain.proceed(request) } answers { response }
        every { request.url } answers { url }
        every { url.toUri() } answers { urlUri }
        every { urlUri.path } answers { PATH }
        every { url.host } answers { HOST }
        every { url.scheme } answers { SCHEMA }
        every { request.method } answers { METHOD }
        every { request.headers } answers { headers }
        every { response.headers } answers { headers }
        every { response.isSuccessful } answers { true }
        every { response.code } answers { CODE }
        every { requestProcessor.getTrackingEvent(request) } answers { EVENT_NAME }

        interceptor = NetworkLoggerInterceptor(logger, networkLogEventMapper, requestProcessor)
    }

    @Test
    fun `given a request to an endpoint with network tracking event when interceptor intercepts then it should return the response, call the mapper and log the event`() {
        interceptor.intercept(chain) shouldBe response

        verify {
            chain.request()
            chain.proceed(request)
            logger.log(match {
                it.name == EVENT_NAME && it.isSourceSupported(NetworkLogSource) &&
                        it.properties == mapOf(
                    ("PATH" to PATH),
                    ("SCHEMA" to SCHEMA),
                    ("METHOD" to METHOD),
                    ("IS_SUCCESS" to "true"),
                    ("RESPONSE_CODE" to "1")
                )
            }
            )
        }
    }

    @Test
    fun `given a requets to an endpoint without network tracking event when interceptor intercepts it should just return the response`() {
        every { requestProcessor.getTrackingEvent(request) } answers { null }
        interceptor.intercept(chain) shouldBe  response

        verify {
            chain.request()
            chain.proceed(request)
            logger wasNot Called
        }

    }
}

private val HEADERS = Headers.headersOf("1", "2")
private const val METHOD = "method"
private const val SCHEMA = "schema"
private const val HOST = "host"
private const val EVENT_NAME = "name"
private const val PATH = "path"
private const val CODE = 1