package com.benrhine.conway.core

class CytoGridFactory {
    final Random rand

    CytoGridFactory(Random random = null ) {
        rand = random ?: new Random()
    }

    static stringInitializedGrid( final gridString ) {
        def grid = null
        if(gridString) {
            final input = gridString.split( "\n" ).collect{ String it -> it.trim() }
            final rows = input.size()

            //The number of columns is derived from the row with the most characters
            final cols = input*.size().max()

            grid = new CytoGrid( rows, cols )
            input.eachWithIndex{ String row, Integer rowNum ->
                row.padRight( cols, "." ).toList().eachWithIndex{ String ch, Integer colNum ->
                    grid.put( rowNum, colNum, CytoGrid.ALIVE_CHAR == ch )
                }
            }
        }
        grid // return
    }

    def randomizedGrid( final rows, final columns ) {
        def grid = null

        if(rows && columns) {
            grid = new CytoGrid( rows, columns )

            for( int row = 0; row < (Integer) rows; row++ ) {
                for( int col = 0; col < (Integer) columns; col++ ) {
                    grid.put( row, col, rand.nextBoolean() )
                }
            }
        }
        grid // return
    }
}
