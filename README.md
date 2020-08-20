# Laboratorio-2-ARSW
## Part I - Before finishing class
### Thread control with wait/notify. Producer/consumer

1. Check the operation of the program and run it. While this occurs, run jVisualVM and     
check the CPU consumption of the corresponding process. Why is this consumption? 
Which is the responsible class? 
Esta situación se da porque, la clase consumidor no tiene ningún mecanismo de pausa permitiendole ejecutar solo con las limitaciones del procesador.
En cambio la clase productor, si tiene mecanismos de pausa generando más lentitud en su producción.
