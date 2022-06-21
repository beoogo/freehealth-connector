package org.taktik.freehealth.middleware.service.impl

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.service.RecipeV4Service

/**
 * @author Bernard Paulus on 14/04/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DummySpringTest(val recipeService: RecipeV4Service) {
    @Test
    fun test() {
        assertNotNull(recipeService)
        assertEquals("RecipeLogicImplTest", RecipeServiceImplTest::class.java.simpleName)
    }
}
