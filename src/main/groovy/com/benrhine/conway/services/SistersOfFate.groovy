package com.benrhine.conway.services

import com.benrhine.conway.core.CytoGrid

class SistersOfFate {

    static nextState(final grid, final row, final col ) {
        def liveNeighbors = getNeighbors((CytoGrid) grid, (Integer) row, (Integer) col ).count{ it }
        liveNeighbors == 3 || (liveNeighbors == 2 && grid.at( row, col ))
    }

    static List<Boolean> getNeighbors(final grid, final row, final col ) {
        List<Boolean> neighbors = []

        ((row-1)..(row+1)).each{ r ->
            ((col-1)..(col+1)).each { c ->

                //The cell must exist and not be itself.
                if( (r != row || c != col) && grid.exists( r, c ) ) {
                    neighbors << (Boolean) grid.at( r, c )
                }
            }
        }

        //Null values are cells that didn't exist.
        neighbors
    }
}
