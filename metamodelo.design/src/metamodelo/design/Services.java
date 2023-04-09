package metamodelo.design;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EObject;

import generacionConAcceleo.*;
import generacionConAcceleo.main.GenerateJava1;

/**
 * The services class used by VSM.
 */
public class Services {
    
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
    public EObject myService(EObject self, String arg) {
       // TODO Auto-generated code
      return self;
    }
    
    public EObject generateCode(EObject self, EObject diagram) throws IOException, CoreException{
    
    	String auxPath = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(self.eResource().getURI().toPlatformString(true))).getRawLocation().toOSString();
    	Integer untilPath = auxPath.lastIndexOf("\\");
    	auxPath = auxPath.substring(0, untilPath);
    	String path = auxPath + "\\generated";
    	path = path.replace("\\", "\\\\");
    	
    	GenerateJava1 generator = new GenerateJava1(diagram, new File(path), new ArrayList<Object>());
    	generator.doGenerate(new BasicMonitor());
    	
    	ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE,new NullProgressMonitor());    	
    	
    	return self;
    }
}
