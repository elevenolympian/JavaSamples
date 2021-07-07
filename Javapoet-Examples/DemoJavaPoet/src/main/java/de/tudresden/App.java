package de.tudresden;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.JavaFile;


import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName; 

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        try {
            System.out.println("Hello Main Method");
            createTypeSpec(); 
            System.out.println(computeRange("computeRange", 2, 5, "+"));
            System.out.println(showCurrentTimeMillis());
    
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static MethodSpec computeRange(String name, int from, int to, String operation) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 1")
                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++")
                .addStatement("result = result " + operation + " i")
                .endControlFlow()
                .addStatement("return result")
                .build(); 
    }

    private static void createTypeSpec() {
        System.out.println( "Hello createTypeSpec!" );
        try {
            MethodSpec methodSpec = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build(); 
            TypeSpec helloTypeSpec = TypeSpec.classBuilder("HelloTUDresden")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(methodSpec)
                .build(); 
            JavaFile file = JavaFile.builder("com.example.helloworld", helloTypeSpec)
                .build(); 

            //Write it into the file
            file.writeTo(System.out); 
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    private static MethodSpec showCurrentTimeMillis() {
        return MethodSpec.methodBuilder("showCurrentTimeMillis")
            .addStatement("long now = $T.currentTimeMillis()", System.class) 
            .beginControlFlow("if ($T.currentTimeMillis() < now) ", System.class) 
            .addStatement("$T.out.println($S)", System.class, "Time travelling, woo hoo!")
            .nextControlFlow("else if ($T.currentTimeMillis() == now)", System.class) 
            .addStatement("$T.out.println($S)", System.class, "Time stood still")
            .nextControlFlow("else")
            .addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward")
            .endControlFlow()
            .build();
    }
}
