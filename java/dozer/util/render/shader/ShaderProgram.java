package dozer.util.render.shader;

import lombok.Getter;
import lombok.SneakyThrows;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.*;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

@Getter
public class ShaderProgram {

  private final int programID;
  private final int vertexID;
  private final int fragmentID;
  private final long initTime = System.currentTimeMillis();

  @SneakyThrows
  public ShaderProgram(String vertex, String fragment) {
    vertexID = loadShader(ShaderProgram.class.getClassLoader().getResourceAsStream(vertex), GL20.GL_VERTEX_SHADER);
    fragmentID = loadShader(ShaderProgram.class.getClassLoader().getResourceAsStream(fragment), GL20.GL_FRAGMENT_SHADER);
    programID = GL20.glCreateProgram();
    GL20.glAttachShader(programID, vertexID);
    GL20.glAttachShader(programID, fragmentID);
    GL20.glLinkProgram(programID);
    GL20.glDeleteShader(vertexID);
    GL20.glDeleteShader(fragmentID);
  }

  public void useShader() {
    GL20.glUseProgram(programID);
  }

  public void stopShader() {
    GL20.glUseProgram(0);
  }

  private static int loadShader(InputStream file, int type){
    StringBuilder shaderSource = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(file));
      String line;
      while((line = reader.readLine())!=null){
        shaderSource.append(line).append("//\n");
      }
      reader.close();
    }catch(IOException e){
      e.printStackTrace();
      System.exit(-1);
    }
    int shaderID = GL20.glCreateShader(type);
    GL20.glShaderSource(shaderID, shaderSource);
    GL20.glCompileShader(shaderID);
    if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
      System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
      System.err.println("Could not compile shader!");
      System.exit(-1);
    }
    return shaderID;
  }

  public void setUniformMat4(String name, boolean bool, FloatBuffer buffer) {
    GL20.glUniformMatrix4(glGetUniformLocation(this.programID, name), bool, buffer);
  }

  public void setUniform2f(String name, float x, float y) {
    GL20.glUniform2f(glGetUniformLocation(this.programID, name), x, y);
  }

  public void setUniform3f(String name, float x, float y, float z) {
    GL20.glUniform3f(glGetUniformLocation(this.programID, name), x, y, z);
  }

  public void setUniform1i(String name, int i) {
    GL20.glUniform1i(glGetUniformLocation(this.programID, name), i);
  }

  public void setUniform1f(String name, float i) {
    GL20.glUniform1f(glGetUniformLocation(this.programID, name), i);
  }

  public int getUniform(String name) {
    return glGetUniformLocation(programID, name);
  }

}