package org.evomaster.core.search

import com.google.inject.*
import org.evomaster.core.BaseModule
import org.evomaster.core.EMConfig
import org.evomaster.core.search.algorithms.MioAlgorithm
import org.evomaster.core.search.algorithms.RandomAlgorithm
import org.evomaster.core.search.onemax.OneMaxIndividual
import org.evomaster.core.search.onemax.OneMaxModule
import org.evomaster.core.search.onemax.OneMaxSampler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class MioAlgorithmOnOneMaxTest {

    val injector: Injector = Guice.createInjector(* arrayOf<Module>(
            OneMaxModule(), BaseModule()))

    @Test
    fun testMIO(){

        val mio = injector.getInstance(Key.get(
                object : TypeLiteral<MioAlgorithm<OneMaxIndividual>>() {}))

        val sampler = injector.getInstance(OneMaxSampler::class.java)
        val config = injector.getInstance(EMConfig::class.java)
        config.maxFitnessEvaluations = 1000

        val n = 20
        sampler.n = n

        val solution = mio.search()

        Assertions.assertEquals(n.toDouble(), solution.overall.computeFitnessScore(), 0.001);
        Assertions.assertEquals(1, solution.individuals.size)
    }
}