package com.epam.rd.autocode.factory.plot;

import java.util.ArrayList;
import java.util.Arrays;

class PlotFactories {


    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
            return new PlotFactory() {
                @Override
                public Plot plot() {
                    final Plot plot = new Plot() {
                        @Override
                        public String toString(){
                            return hero.name() +" is great. " + hero.name() +" and "
                                    + beloved.name() + " love each other. " + villain.name()
                                    + " interferes with their happiness but loses in the end.";
                        }

                    }; return plot;
                }
            };
        }


    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new PlotFactory() {
            @Override
            public Plot plot() {
                final Plot plot2 = new Plot() {
                    @Override
                    public String toString() {
                        return hero.name() + " feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - " + epicCrisis.name()+"." + " "+ hero.name() + " stands up against it, but with no success at first.But putting " +
                                "self together and help of friends, including spectacular funny " + funnyFriend.name() +
                                " restore the spirit and" + " " + hero.name() + " overcomes the crisis and gains gratitude and respect";
                    }
                };
                return  plot2;
            }
        };

    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return  new PlotFactory() {


            @Override
            public Plot plot() {
               final Plot plot = new Plot() {
                   @Override
                   public String toString() {
                       String hh = "";
                       for ( int i = 0; i< heroes.length -1 ; i++){
                           hh += "brave " + heroes[i].name() + ", ";
                       }
                       hh += "brave " + heroes[heroes.length -1].name();
                       return epicCrisis.name()  +
                               " threatens the world. But " +hh + " on guard. So, no way that intrigues " +
                               "of " + villain.name()+ " overcome the willpower of inflexible heroes";
                   }
               };
               return plot;
            }
        };
    }
}


 

