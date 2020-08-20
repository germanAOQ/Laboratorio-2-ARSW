# Laboratorio-2-ARSW
## Part I - Before finishing class
### Thread control with wait/notify. Producer/consumer

1. Check the operation of the program and run it. While this occurs, run jVisualVM and     
check the CPU consumption of the corresponding process. Why is this consumption? 
Which is the responsible class?     
Esta situación se da porque, la clase consumidor no tiene ningún mecanismo de pausa permitiendole ejecutar solo con las limitaciones del procesador.
En cambio la clase productor, si tiene mecanismos de pausa generando más lentitud en su producción.

2. Make the necessary adjustments so that the solution uses the CPU more efficiently, taking into account that - for now - production is slow and consumption 
is fast. Verify with JVisualVM that the CPU consumption is reduced. 
Los cambios que se hicieron fueron dar un mayor tiempo de consumo y un menor tiempo de producción. Se podría llegar a la conclusión que consumir cuesta más que producir.      

3. Make the producer now produce very fast, and the consumer consumes slow. Taking into account that the producer knows a Stock limit 
(how many elements he should have, at most in the queue), make that limit be respected. Review the API of the collection used as a 
queue to see how to ensure that this limit is not exceeded. Verify that, by setting a small limit for the 'stock', there is no high 
CPU consumption or errors.
El tamaño máximo de la clase LinkedBlockingQueue es 2147483647.
