import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.event.LogSource
import com.oscarg798.lomeno.exceptions.LogSourceNotSupportedException
import com.oscarg798.lomeno.logger.Logger
import com.oscarg798.lomeno.logger.Lomeno
import io.mockk.Called
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

object FirebaseSource : LogSource {
    override val name: String
        get() = "F"
}

object CrashlytcisSource : LogSource {
    override val name: String
        get() = "G"
}

object AnotherSource : LogSource {
    override val name: String
        get() = "H"
}


class LomenoTest {

    private val firebaseLogger = relaxedMockk<Logger>()
    private val crashlytcisLogger = relaxedMockk<Logger>()
    private val event = relaxedMockk<LogEvent>()
    private lateinit var logger: Lomeno

    @Before
    fun setup() {
        every { event.isSourceSupported(FirebaseSource) } answers { false }
        every { event.isSourceSupported(CrashlytcisSource) } answers { false }
        every { event.isSourceSupported(AnotherSource) } answers { false }
        logger = Lomeno(
            mapOf(
                (FirebaseSource to listOf(firebaseLogger)),
                (CrashlytcisSource to listOf(crashlytcisLogger))
            )
        )
    }

    @Test
    fun `given an event supported by one source when log is called then it should be logged just to the child logger that support the source`() {
        every { event.isSourceSupported(FirebaseSource) } answers { true }

        logger.log(event)

        verify {
            firebaseLogger.log(event)
            crashlytcisLogger wasNot Called
        }
    }

    @Test
    fun `given an event supported by multiple source when log is called then it should be logged is all the sources`() {
        every { event.isSourceSupported(FirebaseSource) } answers { true }
        every { event.isSourceSupported(CrashlytcisSource) } answers { true }

        logger.log(event)

        verify {
            firebaseLogger.log(event)
            crashlytcisLogger.log(event)
        }
    }

    @Test(expected = LogSourceNotSupportedException::class)
    fun `given an event with a source not supported by any child logger when log is called then it should throw LogSourceNotSupportedException`() {
        logger.log(event)

        verify {
            firebaseLogger wasNot Called
            crashlytcisLogger wasNot Called
        }
    }

    @Test
    fun `given a unique identifier for the event when identify is called then it should identify in all the loggers`() {
        logger.identify("1")

        verify {
            firebaseLogger.identify("1")
            crashlytcisLogger.identify("1")
        }
    }

}