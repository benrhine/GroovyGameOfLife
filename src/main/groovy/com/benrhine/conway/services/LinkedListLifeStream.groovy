package com.benrhine.conway.services

import com.benrhine.conway.core.CytoGrid
import groovy.transform.CompileStatic

/**
 * LifeMachine handles progressing an initial CellGrid through each of it's states based on The Game of Life. History
 * is maintained for each new generation of cells, making it possible to go backwards in time as well as forward.
 *
 * TODO - maximum history size to reduce memory footprint.
 *
 * @see "http://en.wikipedia.org/wiki/Conway's_Game_of_Life"
 * @author Ben Rhine
 */
@CompileStatic
class LinkedListLifeStream extends LifeStream {

    protected final LinkedList<CytoGrid> history

    LinkedListLifeStream(final CytoFate fate) {
        super(fate)
        this.history = new LinkedList<CytoGrid>()
    }

    void reset() {
        super.reset()
        history.clear()
        history.add(initialState)
    }

    /**
     * Returns a {@CellGrid} by transitioning forward `num` states.  All states generated to produce the result will be
     * retained in the history.
     *
     * @param num
     * @return
     */
    final CytoGrid forward(final Integer steps = 1 ) {
        if( !initialState ) {
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

    /**
     * Returns the {@CellGrid} from `steps` in the history.
     * @param steps
     * @return
     */
    final CytoGrid back(final Integer steps = 1 ) {
        if(steps && steps > 0 && steps <= history.size()) {
            for (int i = 0; i < steps; i++) {
                generation--
                history.removeLast()
            }
        }
        history.last()
    }
}

