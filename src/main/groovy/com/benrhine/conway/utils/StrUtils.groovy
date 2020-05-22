package com.benrhine.conway.utils

import com.benrhine.conway.core.CytoGrid
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options

final class StrUtils {

    /**
     * Print the cell grid.
     *
     * @param grid
     * @param generation
     * @return
     */
    static void printIteration(final String title, final CytoGrid grid, final Long generation ) {
        println "${title} ${generation}" + "\n" + "-----------------" + "\n" + grid + "\n"
    }


    static void printUsage(final Options opts ) {
        new HelpFormatter().printHelp( "java -jar game-of-life.jar", opts )
    }

    static void printGameSummary(final long startTime, final int generations) {
        final Date endDate = new Date()
        final long endTime = endDate.getTime()
        final long runTime = endTime - startTime

        println("Started Game of life at: " + startTime.toString())
        println("Completing Game of life at: " + endTime.toString())
        println("Game of life took " + runTime.toString() + " millis to complete for " + generations.toString() + " generations.")
    }
}
