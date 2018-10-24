# fluidDynamics-javaSwing

#Fluid Dynamics Simulation
Created a ParticleCell class which contains an array of particles
Created a FluidFrame class that can hold a two dimensional array of cells/values 
Created a Rule class which determines collision results
Created a FrameFluidSim that holds multiple Frames and can call the Rule class repeatedly to create a list of successive frames

#UI
Created Use Cases that describe program’s operation (rule selection, process start/stop, display control)
Extended from the FDApp abstract class and made own User Interface application 
Added a ComboBox to select an available rule 
Had an input field to specify the maximum number of steps to calculate
Added a start button to generate a full simulation, and a stop button for early processing termination
Allowed the user to pause the simulation
Displayed the results of Fluid Dynamics simulation onto a graphical panel display
