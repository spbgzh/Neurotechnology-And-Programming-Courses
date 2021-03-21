package com.epam.rd.autocode.factory.plot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlotFactoriesTest {
    private PlotFactories factories = new PlotFactories();

    @Test
    public void classicDisneyPlotFactory() {

        PlotFactory mermaidPlotFactory = factories.classicDisneyPlotFactory(() -> "Ariel", () -> "Eric", () -> "Ursula");
        PlotFactory beautyAndTheBeastPlotFactory = factories.classicDisneyPlotFactory(() -> "Belle", () -> "Beast", () -> "Gaston");
        PlotFactory lionKingPlotFactory = factories.classicDisneyPlotFactory(() -> "Simba", () -> "Nala", () -> "Scar");

        assertEquals(
                "Ariel is great. Ariel and Eric love each other. Ursula interferes with their happiness but loses in the end.",
                mermaidPlotFactory.plot().toString()
        );
        assertEquals(
                "Belle is great. Belle and Beast love each other. Gaston interferes with their happiness but loses in the end.",
                beautyAndTheBeastPlotFactory.plot().toString()
        );
        assertEquals(
                "Simba is great. Simba and Nala love each other. Scar interferes with their happiness but loses in the end.",
                lionKingPlotFactory.plot().toString()
        );
    }

    @Test
    public void contemporaryDisneyPlotFactory() {
        PlotFactory mulanPlotFactory = factories.contemporaryDisneyPlotFactory(() -> "Mulan", () -> "Hun invasion", () -> "little dragon Mushu");
        PlotFactory frozenPlotFactory = factories.contemporaryDisneyPlotFactory(() -> "Anna", () -> "her sister Elsa is overwhelmed by her magical powers", () -> "snowman Olaf");
        PlotFactory moanaPlotFactory = factories.contemporaryDisneyPlotFactory(() -> "Moana", () -> "a blight strikes the island", () -> "shape-shifting demigod Maui");

        assertEquals(
                "Mulan feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - Hun invasion. Mulan stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny little dragon Mushu restore the spirit and Mulan overcomes the crisis and gains gratitude and respect",
                mulanPlotFactory.plot().toString()
        );
        assertEquals(
                "Anna feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - her sister Elsa is overwhelmed by her magical powers. Anna stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny snowman Olaf restore the spirit and Anna overcomes the crisis and gains gratitude and respect",
                frozenPlotFactory.plot().toString()
        );
        assertEquals(
                "Moana feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - a blight strikes the island. Moana stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny shape-shifting demigod Maui restore the spirit and Moana overcomes the crisis and gains gratitude and respect",
                moanaPlotFactory.plot().toString()
        );
    }

    @Test
    public void marvelPlotFactory() {
        PlotFactory ironManPlotFactory = factories.marvelPlotFactory(
                new Character[]{() -> "Iron Man"},
                () -> "Ten Rings is about to access Stark Industries technologies and that",
                () -> "Obadiah Stane");
        PlotFactory avengersPlotFactory = factories.marvelPlotFactory(
                new Character[]{() -> "Iron Man", () -> "Captain America", () -> "Hulk", () -> "Thor", () -> "Black Widow", () -> "HawkEye"},
                () -> "Chitauri Invasion",
                () -> "Loki");
        PlotFactory guardiansOfTheGalaxyPlotFactory = factories.marvelPlotFactory(
                new Character[]{() -> "Star-Lord", () -> "Gamora", () -> "Drax", () -> "Groot", () -> "Rocket"},
                () -> "Kree Invasion",
                () -> "Ronan the Accuser");

        assertEquals(
                "Ten Rings is about to access Stark Industries technologies and that threatens the world. But brave Iron Man on guard. So, no way that intrigues of Obadiah Stane overcome the willpower of inflexible heroes",
                ironManPlotFactory.plot().toString()
        );
        assertEquals(
                "Chitauri Invasion threatens the world. But brave Iron Man, brave Captain America, brave Hulk, brave Thor, brave Black Widow, brave HawkEye on guard. So, no way that intrigues of Loki overcome the willpower of inflexible heroes",
                avengersPlotFactory.plot().toString()
        );
        assertEquals(
                "Kree Invasion threatens the world. But brave Star-Lord, brave Gamora, brave Drax, brave Groot, brave Rocket on guard. So, no way that intrigues of Ronan the Accuser overcome the willpower of inflexible heroes",
                guardiansOfTheGalaxyPlotFactory.plot().toString()
        );
    }
}