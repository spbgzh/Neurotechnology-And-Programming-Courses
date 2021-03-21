# Design Patterns

## Decorator
**Decorator** is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

Implement [`com.epam.rd.autocode.decorator.Decorators`](src\main\java\com\epam\rd\autocode\decorator\Decorators.java) method:
- `evenIndexElementsSubList` - return a decorator, that manages only elements with even indices in a source list.
    Decorated list should support "read" methods: `get()`, `size()`, `iterator()`.
    
## Factory
**Abstract Factory** is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.

Implement [`com.epam.rd.autocode.factory.plot.PlotFactories`](src\main\java\com\epam\rd\autocode\factory\plot\PlotFactories.java) methods:
- `classicDisneyPlotFactory` - return a factory that creates a classic Disney plot (refer to test cases).
- `contemporaryDisneyPlotFactory` - return a factory that creates a contemporary Disney plot (refer to test cases).
- `marvelPlotFactory` - return a factory that creates a Marvel plot (refer to test cases).
    
## Iterator
**Iterator** is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation.

Implement [`com.epam.rd.autocode.iterator.Iterators`](src\main\java\com\epam\rd\autocode\iterator\Iterators.java) methods:
- `intArrayTwoTimesIterator` - return an Iterator that iterates over given array but returns each array element 2 times.
- `intArrayThreeTimesIterator` - return an Iterator that iterates over given array but returns each array element 3 times.
- `intArrayFiveTimesIterator` - return an Iterator that iterates over given array but returns each array element 5 times.
- `table` - return an Iterator that iterates over cells - pairs of given columns and rows.
    
## Observer
**Observer** is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.

Implement [`com.epam.rd.autocode.observer.git.GitRepoObservers`](src\main\java\com\epam\rd\autocode\observer\git\GitRepoObservers.java) methods:
- `newRepository` - return a Repository. It supports commits to various branches and merges between branches.
Also, it supports WebHooks - observers that observes commit or merge events.
- `mergeToBranchWebHook` - return a WebHook that observes merge events for a target branch.
- `commitToBranchWebHook` - return a WebHook that observes commit events for a target branch.    

## Strategy
**Strategy** is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.

*CardDealingStrategy* is a strategy of dealing cards for a card game.
Each game defines how many cards should be dealt to a player and what additional card stacks should be dealt as well.

Result of it is a Map containing named card stacks (as Lists).
Each player stack has a name by its number: "Player 1", "Player 2", ...
Additional stacks varies from game to game.
The rest of the card deck becomes a "Remaining" stack.

Note, at first, cards are dealt to players, one per round and then cards are dealt to additional stacks and the remaining deck becomes a "Remaining" stack.

Implement [`com.epam.rd.autocode.startegy.cards.CardDealingStrategies`](src\main\java\com\epam\rd\autocode\startegy\cards\CardDealingStrategies.java) methods:
- `texasHoldemCardDealingStrategy` - return a CardDealingStrategy for Texas Hold'em Poker.
2 cards per player, 5 cards in additional "Community" stack.
- `classicPokerCardDealingStrategy` - return a CardDealingStrategy for Classic Poker.
5 cards per player, no additional stacks.
- `bridgeCardDealingStrategy` - return a CardDealingStrategy for Bridge.
13 cards per player, no additional stacks. 
- `foolCardDealingStrategy` - return a CardDealingStrategy for Fool.
6 cards per player, 1 card in additional "Trump card" stack.
