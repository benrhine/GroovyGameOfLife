package com.benrhine.conway.services

import com.benrhine.conway.core.CytoGrid
import com.benrhine.conway.core.CytoGridFactory
import com.benrhine.conway.util.ConwaySpecification

class LifeStreamSpec extends ConwaySpecification {
    final factory         = new CytoGridFactory()
    final sistersOfFate   = Mock(SistersOfFate)

    final lifeStream = new LifeStream( sistersOfFate )

    def "when initialized, no history exists." () {
        given:
            final grid = new CytoGrid(10, 10)
        when:
            lifeStream.initialize( grid )
        then:
            lifeStream.generation == 0L
            lifeStream.history.size() == 1
    }

    def "LifeStream must be initialized before transitions can occur" () {
        given:
            final grid = new CytoGrid( 10, 10 )
        when:
            lifeStream.forward()
        then:
            thrown(IllegalStateException)
        when:
            lifeStream.initialize( grid )
            lifeStream.forward()
        then:
            notThrown(IllegalStateException)
    }

    def "The SistersOfFate is used to generate the next state of each cell when transitioning forward" () {
        given:
            final grid = factory.randomizedGrid( 10, 10 )
            lifeStream.initialize( grid )
        when:
            final result = lifeStream.forward()
        then:
        sistersOfFate.nextState( grid, _, _ ) >> true
        //100 * sistersOfFate.nextState( grid, _, _ ) >> true
//        println result.values().count{ it }
//        result.values().count{ it } == 23
        lifeStream.history.size() == 2
    }

    def "Previous states can be returned using the back() method"() {
        given:
            final grid = factory.randomizedGrid( 10, 10 )
            lifeStream.initialize( grid )
        when:
            final result1 = lifeStream.forward()
            lifeStream.forward( 2 )
            final result2 =lifeStream.back( 2 )
        then:
            lifeStream.history.size() == 2
            result1 == result2
    }
    def "LifeStream produces complete cell states based on The Game of Life rules"() {
        given:
            final lifeStream = new LifeStream( new SistersOfFate() )
            final grid = factory.stringInitializedGrid( input )
            final expected = factory.stringInitializedGrid( output )
        when:
            lifeStream.initialize( grid )
        then:
            lifeStream.forward() == expected
        where:
            input = "......O.\n" +
                    "OOO...O.\n" +
                    "......O.\n" +
                    "........\n" +
                    "...OO...\n" +
                    "...OO..."
            output = ".O......\n" +
                    ".O...OOO\n" +
                    ".O......\n" +
                    "........\n" +
                    "...OO...\n" +
                    "...OO..."
    }
}
