#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <iostream>
#include <unistd.h>

int main(void)
{
    GLFWwindow* window;
    
    //Trying to initialize the program
    if(!glfwInit()) 
        return -1;
    
    //Setting up the version of glfw to be
    //only the version 3
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

    //Setting up the core profile of opengl, for having access
    //with the new functionality
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    //Creating and setting up the window, and checking if 
    //it was created properly
    window = glfwCreateWindow(800, 800, "Test", NULL, NULL);
    if(window == NULL) {

        std::cout << "Failed to create GLFW Window" << std::endl;
        glfwTerminate();
        return -1;
    }

    //OpenGL will keep looking at the current
    //window that we have created
    glfwMakeContextCurrent(window);

    //Setting up a window with a blue navy color
    gladLoadGL();                           //load necessary configurations
    glViewport(0,0,800,800);                //Area of the window that OpenGL will act 
    glClearColor(1.0f,0.64f,0.00f,1.0f);    //Setting up the aRGB of the color (Red, green, blue, alpha)
    glClear(GL_COLOR_BUFFER_BIT);           //Clean the back buffer and assign the new color to it
    glfwSwapBuffers(window);                //Swap the back buffer with the front buffer

    //Run the window until it is closed
    //ex: Click the 'x' or a function tells the
    //window to close
    while(!glfwWindowShouldClose(window)) {

        double mouse_x, mouse_y;
        glfwPollEvents();  //Process all openGL events

        //Get the current cursor position
        glfwGetCursorPos(window,&mouse_x,&mouse_y); 
        
        //Verify if the mouse 1 button was pressed
        if(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS) {
            std::cout<< mouse_x << " ; " << mouse_y << std::endl;
            sleep(1); //making the program sleep so that it only activate it once
        }
    }
    
    //Destroy and terminate the program
    //(Necessary funcitons to call!)
    glfwDestroyWindow(window);
    glfwTerminate();
    return 0;
}