//package controllers;
//
//import akka.actor.ActorRef;
//import akka.actor.Props;
//import akka.actor.UntypedActor;
//
//public class MyWebSocketActor extends UntypedActor{
//
//	public static Props props(ActorRef out){
//		return Props.create(MyWebSocketActor.class, out);
//	}
//	
//	private final ActorRef out;
//	
//	public MyWebSocketActor(ActorRef out){
//		this.out = out;
//	}
//	
//	@Override
//	public void onReceive(Object message) throws Exception {
//		if( message instanceof String){
//			out.tell("Nachricht erhalten " +message, self());
//			System.out.println("Nachricht erhalten " +message);
//		}
//	}
//	
//
//}
