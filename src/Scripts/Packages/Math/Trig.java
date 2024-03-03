package Scripts.Packages.Math;

import Scripts.Functions.Math.Trig.*;
import Scripts.Packages.BasePackage;

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
