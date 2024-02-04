package Scripts.Packages;

import Scripts.Functions.Console.Error;
import Scripts.Functions.Console.*;

public class Console extends BasePackage{
    public Console(){
        super();
        childFunctions.add(new Log());
        childFunctions.add(new Warn());
        childFunctions.add(new Error());
    }
}
