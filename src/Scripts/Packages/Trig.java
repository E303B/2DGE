package Scripts.Packages;

import Scripts.Functions.Math.Trig.*;

public class Trig extends BasePackage {
    public Trig() {
        super();
        this.childFunctions.add(new Sin());
        this.childFunctions.add(new Cos());
        this.childFunctions.add(new Tan());
    }
}
