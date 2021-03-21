package com.efimchick.tasks.risky;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.efimchick.tasks.risky.util.RussianRoulette;

public class RiskyShot {

    final int input;
    final RussianRoulette roulette;


    public RiskyShot(final int input,
                     final RussianRoulette roulette) {
        this.input = input;
        this.roulette = roulette;
    }

    public void handleShot() /*You may not add "throws" here*/ {
        // handle method call
        //roulette.shot(input);
        try {
            roulette.shot(input);
        } catch (FileNotFoundException test) {

            throw new IllegalArgumentException("File is missing", test);

        } catch (IOException test) {

            throw new IllegalArgumentException("File error", test);

        }
        catch (UnsupportedOperationException test) {



            throw new UnsupportedOperationException("5", test);


        }
        catch (NumberFormatException test) {


            new RiskyShot(input+2, roulette).handleShot();

        }
        catch (ArithmeticException test) {

           new RiskyShot(input+1, roulette).handleShot();


        }catch (Exception test) {
            test.printStackTrace();
        }
    }
}
