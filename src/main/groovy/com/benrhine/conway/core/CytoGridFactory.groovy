package com.benrhine.conway.core

import groovy.transform.CompileStatic

/**
 * Generates a new grid based on inputs
 *
 * @author Ben Rhine
 */
@CompileStatic
class CytoGridFactory {

    final Random rand

    CytoGridFactory(Random random = null ) {
        rand = random ?: new Random()
    }

    /**
     * Generate a grid from it's string representation.  A line-feed delimited list of "O" and "." is expected.
     *
     * The grid will be based on the largest number of columns specified in any row.  If a row contains less values than
     * columns in the grid, the remaining values will be considered dead cells.
     *
     * <p>
     *   <pre>
     *   ......O.
     *   OOO...O.
     *   ......O.
     *   ........
     *   ...OO...
     *   ...OO...
     *   </pre>
     * </p>
     *
     * @param grid
     * @return
     */
    static CytoGrid stringInitializedGrid( final String gridString ) {
        CytoGrid grid = null
        if(gridString) {
            final String[] input = gridString.split( "\n" ).collect{ String it -> it.trim() }
            final int rows = input.size()

            //The number of columns is derived from the row with the most characters
            final int cols = input*.size().max()

            grid = new CytoGrid( rows, cols )
            input.eachWithIndex{ String row, int rowNum ->
                row.padRight( cols, "." ).toList().eachWithIndex{ String ch, int colNum ->
                    grid.put( rowNum, colNum, CytoGrid.ALIVE == ch )
                }
            }
        }
        grid // return
    }

    /**
     * Uses the given rows and columns to create a grid and initializes it with random values.
     * @param rows
     * @param cols
     * @return
     */
    final CytoGrid randomizedGrid( final int rows, final int cols ) {
        CytoGrid grid = null

        if(rows && cols) {
            grid = new CytoGrid( rows, cols )

            for( int row = 0; row < rows; row++ ) {
                for( int col = 0; col < cols; col++ ) {
                    grid.put( row, col, rand.nextBoolean() )
                }
            }
        }
        grid // return
    }

}
