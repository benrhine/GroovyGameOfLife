package com.benrhine.conway

import com.benrhine.conway.core.CytoGrid
import com.benrhine.conway.core.CytoGridFactory
import com.benrhine.conway.services.ArrayListLifeStream
import com.benrhine.conway.services.LifeStream
import com.benrhine.conway.services.LinkedListLifeStream
import com.benrhine.conway.services.CytoFate
import com.benrhine.conway.utils.StrUtils
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options

import static java.lang.System.exit

class GroovyGameOfLife {
    final static CytoGridFactory gridFactory = new CytoGridFactory()
    final static CytoFate fate = new CytoFate()

    static void main( String[] args ) {
        final Date startDate = new Date()
        final long startTime = startDate.getTime()

        final Options opts = new Options()
        opts.addOption( "r", "randomize", true, "Start the game with a randomly generated grid of the specified size.  Example: --randomize 12x12" )
        opts.addOption( "f", "file", true, "Start the game with a cell in the specified file." )
        opts.addOption( "g", "generations", true, "The number of generations to print. Defaults to 1")
        opts.addOption( "l", "list", true, "List implementation to use. Defaults to LinkedList")

        final cmd = new DefaultParser().parse( opts, args )

        final CytoGrid initialGrid
        final Integer generations = cmd.getOptionValue( "g", "1" ).toInteger()

        if ( cmd.hasOption( "r" ) && cmd.getOptionValue("r").contains("x") ) {
            final size = cmd.getOptionValue( "r" ).split("x", 2)
            initialGrid = gridFactory.randomizedGrid( size[0].toInteger(), size[1].toInteger() )

        } else if( cmd.hasOption( "f" ) ) {

            //TOOD handle exceptions if file can't be read
            final string = new File( cmd.getOptionValue( "f" ) )?.text
            initialGrid = gridFactory.stringInitializedGrid( string )

        } else {
            System.err.println( "You must specify either --randomize or --file options" )
            StrUtils.printUsage( opts )
            exit(0)
            return
        }

        final String listType = cmd.getOptionValue( "l", "linked" )

        if (listType.equalsIgnoreCase("array")) {
            println("Using ArrayList for life stream ...")

            lifeCycle(new ArrayListLifeStream(fate), initialGrid, generations)

        } else {
            println("Using LinkedList for life stream ...")

            lifeCycle(new LinkedListLifeStream(fate), initialGrid, generations)
        }

        StrUtils.printGameSummary(startTime, generations)
    }

    static void lifeCycle(final LifeStream life, final CytoGrid initialGrid, final Integer generations) {
        life.initialize(initialGrid)

        StrUtils.printIteration('Initial Population: Iteration ', initialGrid, 0)

        CytoGrid current
        CytoGrid previous = initialGrid

        for (int i = 0; i < generations; i++) {

            current = life.forward()

            StrUtils.printIteration('Generation', current, life.generation)

            // Extinction occurs when a grid is produce with no life.
            if (!current.values().contains(true)) {
                println "\n\nExtinction occurred at generation ${life.generation}. No more life is possible.\n\n"
                break
            }

            // Uptopia occurs when cells align in a way that their state will never change.  This is usually represented
            // by ring formations, where the cell(s) in the center will never have enough neighbors and the ring cells
            // always have two.
            if (current == previous) {
                println "\n\nUtopia occurred at generation ${life.generation - 1}.  All living cells will live forever, and no more new cells can come to life."
                break
            }
            previous = current
        }
    }

}
