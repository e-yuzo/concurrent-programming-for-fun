/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.task_05.ex_01;

/**
 *
 * @author yuzo
 */
public interface ReadWriter {
    
    public void startRead() throws InterruptedException;

    public void endRead() throws InterruptedException;

    public void startWrite() throws InterruptedException;

    public void endWrite() throws InterruptedException;
    
}
