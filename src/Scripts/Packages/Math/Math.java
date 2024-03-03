package Scripts.Packages.Math;

import Scripts.Functions.Math.*;
import Scripts.Packages.BasePackage;

public class Math extends BasePackage {
    public Math() {
        super();
        this.childFunctions.add(new Plus());
        this.childFunctions.add(new Minus());
        this.childFunctions.add(new Multiply());
        this.childFunctions.add(new Divide());
        this.childFunctions.add(new Power());
        this.childFunctions.add(new Mod());

        this.childPackages.add(new Func());
        this.childPackages.add(new Trig());
    }
}
