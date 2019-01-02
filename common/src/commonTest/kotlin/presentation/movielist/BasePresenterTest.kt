package presentation.movielist

import InjectMocksRule
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import presentation.BasePresenter
import presentation.BaseView
import kotlin.test.BeforeTest
import kotlin.test.Test

class BasePresenterTest {

    lateinit var basePresenter: BasePresenter

    @MockK
    lateinit var context: Dispatchers

    @MockK
    lateinit var baseView: BaseView

    @BeforeTest
    fun setup(){
        InjectMocksRule.createMockK(this)

        basePresenter = BasePresenter(context.Default, baseView)
    }

    @Test
    fun `onDestory will destroy job`(){

        basePresenter.onDestroy()

    }


}