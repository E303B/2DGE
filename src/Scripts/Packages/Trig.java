package Scripts.Packages;

import Scripts.Functions.Math.Trig.*;

public class Trig extends BasePackage {
    public Trig() {
        super();
        this.childFunctions.add(new Sin());
        this.childFunctions.add(new Cos());
        this.childFunctions.add(new Tan());

        this.childFunctions.add(new Asin());
        this.childFunctions.add(new Acos());
        this.childFunctions.add(new Atan());
    }
}
