import java.io.*;

public class FileReader {

    private Scene scene;

    public Scene loadFile(String fileName) {
        int id = 0;

        scene = new Scene();

        String line;

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));

            int lineIndex = 0;

            while ((line = br.readLine()) != null) {
                switch(lineIndex){
                    case 1:
                        scene.setWidth(Integer.parseInt(line));
                        break;
                    case 2:
                        scene.setHeigth(Integer.parseInt(line));
                        break;
                    case 3:
                        String[] vars = line.split(",");
                        Double[] variables = new Double[vars.length];
                        for(int i = 0; i < variables.length; i++) {
                            variables[i] = Double.parseDouble(vars[i]);
                        }
                        scene.setBackgroundColor(new Vector3(variables[0], variables[1], variables[2]));
                        break;
                    case 5:
                        scene.setCamera(new Camera(scene.getWidth(), scene.getHeigth(), Integer.parseInt(line)));
                        break;
                    case 6:
                        vars = line.split(",");
                        variables = new Double[vars.length];
                        for(int i = 0; i < variables.length; i++) {
                            variables[i] = Double.parseDouble(vars[i]);
                        }
                        scene.getCamera().setPosition(new Vector3(variables[0], variables[1], variables[2]));
                        break;
                }
                if(lineIndex >= 8 && !line.equals("[EOF]")){
                    processObjects(line, id);
                    id++;
                }else if(line.equals("[EOF]")) {
                    break;
                }
                lineIndex ++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return scene;
    }

    private void processObjects(String line, int id) {
        String[] vars = line.split(",");
        Double[] variables = new Double[vars.length - 1];
        for(int i = 0; i < variables.length; i++) {
            variables[i] = Double.parseDouble(vars[i + 1]);
        }
        if(vars[0].equals("L")) {
            scene.getLights().add(new Sphere(new Vector3(variables[0], variables[1], variables[2]), 1, new Vector3(variables[3], variables[4], variables[5]), id));
        }else if(vars[0].equals("S")) {
            scene.getObjects().add(new Sphere(new Vector3(variables[0], variables[1], variables[2]), variables[3], new Vector3(variables[4], variables[5], variables[6]), id));
        } else if(vars[0].equals("P")) {
            scene.getObjects().add(new Plane(new Vector3(variables[0], variables[1], variables[2]), new Vector3(variables[3], variables[4], variables[5]), id));
        }
    }
}
