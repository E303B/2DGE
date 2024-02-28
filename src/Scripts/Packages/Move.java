package Scripts.Packages;

import Scripts.Functions.Move.*;

public class Move  extends BasePackage{
    public Move(){
        super();
        this.childFunctions.add(new To());
        this.childFunctions.add(new By());
    }
}
