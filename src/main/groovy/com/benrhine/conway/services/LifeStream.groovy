package com.benrhine.conway.services

import com.benrhine.conway.core.CytoGrid

class LifeStream {

    protected Long generation = 0
    protected CytoFate fate
    protected CytoGrid initialState

    LifeStream(final CytoFate fate) {
        this.fate  = fate ?: null
    }

    /**
     * Initialize the LifeMachine with the provide grid.
     *
     * @param cellGrid
     * @return
     */
    void initialize(final CytoGrid cellGrid ) {
        initialState = cellGrid
        reset()
    }

    void reset() {
        generation = 0
    }

    final Long getGeneration() {
        this.generation
    }

    /**
     * Transitions the given {@CellGrid} to it's next state using the {@CytoFate}.
     * @param grid
     * @return
     */
    protected CytoGrid transition(final CytoGrid grid ) {
        final CytoGrid next = new CytoGrid( grid.rows, grid.cols )

        for( int row = 0; row < grid.rows; row++ ) {
            for( int col = 0; col < grid.cols; col++ ) {
                next.put( row, col, fate.nextState( grid, row, col ) )
            }
        }

        next // return
    }
}
