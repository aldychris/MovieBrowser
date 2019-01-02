import io.mockk.MockKAnnotations

class InjectMocksRule {

    companion object {
        fun createMockK(testClass: Any) {
            MockKAnnotations.init(testClass, relaxUnitFun = true)
        }
    }
}