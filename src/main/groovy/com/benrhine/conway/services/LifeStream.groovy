package com.benrhine.conway.services

import com.benrhine.conway.core.CytoGrid

class LifeStream {
    private Long generation = 0
    private SistersOfFate sistersOfFate
    private LinkedList<CytoGrid> history

    private CytoGrid initialState

    LifeStream( SistersOfFate sistersOfFate ) {
        this.sistersOfFate  = sistersOfFate ?: null
        this.history = new LinkedList<CytoGrid>()
    }

    def initialize( final cellGrid ) {
        initialState = cellGrid
        reset()
    }

    def reset() {
        history.clear()
        generation = 0
        history.add initialState
    }

    def forward( final Integer steps = 1 ) {
        if( !initialState ) {
            //TODO IllegalStateException is not the best exception to be throwing
            throw new IllegalStateException( 'LifeMachine must be initialized first' )
        }

        if(steps && steps > 0) {
            for (int i = 0; i < steps; i++) {
                generation++
                history.add transition(history.last())
            }
        }
        history.last()
    }

    def back( final Integer steps = 1 ) {
        if(steps && steps > 0 && steps <= history.size()) {
            for (int i = 0; i < steps; i++) {
                generation--
                history.removeLast()
            }
        }
        history.last()
    }

    protected transition(final grid ) {
        def next = new CytoGrid( grid.rows, grid.columns )

        for( int row = 0; row < (Integer) grid.rows; row++ ) {
            for( int col = 0; col < (Integer) grid.columns; col++ ) {
                next.put( row, col, sistersOfFate.nextState( grid, row, col ) )
            }
        }
        next // return
    }
}
