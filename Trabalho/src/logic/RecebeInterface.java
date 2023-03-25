package logic;

public class RecebeInterface implements Runnable {

    public Recebedor r;
    public ClienteTeste cliente;

    public RecebeInterface(Recebedor r,ClienteTeste cliente){
        this.r=r;
        this.cliente=cliente;
    }
    @Override
  public void run() {
    // TODO Auto-generated method 
    boolean teste=true;
    while(teste==true){
      if(r.buffer!=null){
        if(r.buffer.size()>0){
            for(int i=0;i<r.buffer.size();i++){
              this.cliente.AreaDoChat.append(r.buffer.get(i));
              r.buffer.remove(i);
            }
        }
      }
    }
  }
}
