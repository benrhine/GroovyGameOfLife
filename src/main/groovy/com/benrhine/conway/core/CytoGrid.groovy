package com.benrhine.conway.core

import com.google.common.collect.ArrayTable
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

//@CompileStatic
@EqualsAndHashCode
class CytoGrid {
    static final String ALIVE_CHAR     = "O"
    static final String NOT_ALIVE_CHAR = "."

    @Delegate final ArrayTable<Integer, Integer, Boolean> state
    final rows
    final columns

    CytoGrid(final rows, final columns) {
        if(rows && rows > 0 && columns && columns > 0) {
            this.rows = rows
            this.columns = columns

            state = ArrayTable.create( (0..(rows-1)), (0..(columns-1)) )
        } else {
            println 'Invalid rows and columns'
        }

    }

    def exists( final row, final columns ) {
        row >= 0 && row < this.rows && columns >= 0 && columns < this.columns  // return boolean
    }

    @Override
    String toString() {
        state.rowMap().collect{ row ->
            row.value.collect{ col -> col.value ? ALIVE_CHAR : NOT_ALIVE_CHAR }.join("")
        }.join( "\n" )
    }
}
