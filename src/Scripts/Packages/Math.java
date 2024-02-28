package Scripts.Packages;

import Scripts.Functions.Math.*;

public class Math extends BasePackage {
    public Math() {
        super();
        this.childFunctions.add(new Plus());
        this.childFunctions.add(new Minus());
        this.childFunctions.add(new Multiply());
        this.childFunctions.add(new Divide());
    }
}
