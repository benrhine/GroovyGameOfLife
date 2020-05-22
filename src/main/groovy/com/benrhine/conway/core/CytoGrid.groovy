package com.benrhine.conway.core

import com.google.common.collect.ArrayTable
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

/**
 *
 * Grid representation of cell. There are two types of cells ALIVE represented by 0 and DEAD represented by .
 *
 * @author Ben Rhine
 */
@CompileStatic
@EqualsAndHashCode
class CytoGrid {
    static final String ALIVE = "O"
    static final String DEAD = "."

    final Integer rows
    final Integer cols
    final @Delegate ArrayTable<Integer, Integer, Boolean> state

    CytoGrid(Integer rows, Integer columns ) {
        if(rows && rows > 0 && columns && columns > 0) {
            this.rows = rows
            this.cols = columns

            state = ArrayTable.create( (0..(rows-1)), (0..(columns-1)) )
        } else {
            println 'Invalid rows and columns'
        }
    }

    /**
     * Determines if the specified cell coordinates exist in this grid.
     * @param row
     * @param col
     * @return
     */
    final Boolean exists( int row, int col ) {
        row >= 0 && row < rows && col >= 0 && col < cols
    }

    @Override
    final String toString() {
        state.rowMap().collect{ row ->
            row.value.collect{ col -> col.value ? ALIVE : DEAD }.join("")
        }.join( "\n" )
    }
}
