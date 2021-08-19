#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <iostream>
#include <unistd.h>

int main(void)
{
    GLFWwindow* window;
    
    if(!glfwInit()) 
        return -1;
    
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    window = glfwCreateWindow(800, 800, "Test", NULL, NULL);
    if(window == NULL) {

        std::cout << "Failed to create GLFW Window" << std::endl;
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(window);

    //Setting up a window with a blue navy color
    gladLoadGL();
    glViewport(0,0,800,800);
    glClearColor(0.07f,0.13f,0.17f,1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
    glfwSwapBuffers(window);

    while(!glfwWindowShouldClose(window)) {

        double x,y;
        glfwPollEvents();
        glfwGetCursorPos(window,&x,&y);
        if(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS) {
            std::cout<< x << " ; " << y << std::endl;
            sleep(1);
        }
    }

    glfwDestroyWindow(window);
    glfwTerminate();
    return 0;
}