package com.benrhine.conway.services

import com.benrhine.conway.core.CytoGridFactory
import com.benrhine.conway.util.ConwaySpecification

class CytoFateSpec extends ConwaySpecification {

    def fate = new CytoFate()

    void "A living cell with 2 or 3 neighbors will survive" () {

        def grid = new CytoGridFactory().stringInitializedGrid( grids )

        expect:

        fate.nextState( grid, 2, 2 )

        where:

        grids << [
                 ".....\n"
               + "..O..\n"
               + "..OO.\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + "..O..\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + "..OO.\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..OO.\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + ".O...\n"
               + "..OO.\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + ".OO..\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + ".OOO.\n"
               + ".....\n",

                 "...\n"
               + ".O.\n"
               + "..O\n"
               + ".O.\n"
               + "...\n"
        ]
    }

    void "A living cell with less than 2 neighbors will die" () {

        def grid = new CytoGridFactory().stringInitializedGrid( grids )

        expect:

        !fate.nextState( grid, 2, 2 )

        where:

        grids << [
                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + "..O..\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..OO.\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + ".O...\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + "..O..\n"
               + "...O.\n"
               + ".....\n",

                 "...\n"
               + "...\n"
               + "..O\n"
               + "...\n"
               + "...\n"
        ]
    }

    void "A living cell with more than 3 neighbors will die" () {

        def grid = new CytoGridFactory().stringInitializedGrid( grids )

        expect:

        !fate.nextState( grid, 2, 2 )

        where:

        grids << [
                 ".....\n"
               + ".OOO.\n"
               + ".OOO.\n"
               + ".OOO.\n"
               + ".....\n",

                 ".....\n"
               + "..OO.\n"
               + "..OO.\n"
               + "...O.\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + ".OOO.\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + ".O.O.\n"
               + "..O..\n"
               + ".O.O.\n"
               + ".....\n",

                 ".....\n"
               + ".O...\n"
               + ".OO..\n"
               + ".OO..\n"
               + ".....\n",

                 ".....\n"
               + ".OOO.\n"
               + "..O..\n"
               + ".O...\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + "..O..\n"
               + ".OOO.\n"
               + ".....\n",

                 "...\n"
               + ".OO\n"
               + ".OO\n"
               + "..O\n"
               + "...\n"
        ]
    }


    void "A dead cell comes alive if it has exactly 3 neighbors"() {

        def grid = new CytoGridFactory().stringInitializedGrid( grids )

        expect:

        fate.nextState( grid, 2, 2 )

        where:

        grids << [
                 ".....\n"
               + ".OOO.\n"
               + ".....\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + "...O.\n"
               + "...O.\n"
               + "...O.\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + ".O.O.\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + ".O...\n"
               + ".....\n"
               + ".O.O.\n"
               + ".....\n",

                 ".....\n"
               + ".O...\n"
               + ".O...\n"
               + ".O...\n"
               + ".....\n",

                 ".....\n"
               + ".OOO.\n"
               + ".....\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + ".....\n"
               + ".OOO.\n"
               + ".....\n",

                 "...\n"
               + ".OO\n"
               + "..O\n"
               + "...\n"
               + "...\n"
        ]
    }

    void "A dead cell will remain dead if it does not have exactly 3 neighbors"() {

        def grid = new CytoGridFactory().stringInitializedGrid( grids )

        expect:

        !fate.nextState( grid, 2, 2 )

        where:

        grids << [
                 ".....\n"
               + ".OOO.\n"
               + ".O.O.\n"
               + ".OOO.\n"
               + ".....\n",

                 ".....\n"
               + ".....\n"
               + ".....\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + "..OO.\n"
               + "...O.\n"
               + "...O.\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + ".O.O.\n"
               + "..O..\n"
               + ".....\n",

                 ".....\n"
               + ".O.O.\n"
               + ".....\n"
               + ".O.O.\n"
               + ".....\n",

                 ".....\n"
               + ".O...\n"
               + ".O.O.\n"
               + ".O...\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + ".....\n"
               + ".....\n"
               + ".....\n",

                 ".....\n"
               + "..O..\n"
               + ".....\n"
               + ".OOO.\n"
               + ".....\n",

                 "...\n"
               + "...\n"
               + "..O\n"
               + "...\n"
               + "...\n"
        ]
    }
}
