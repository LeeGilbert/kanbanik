package com.googlecode.kanbanik.client.modules.lifecyclelisteners;

import com.googlecode.kanbanik.client.ModuleActivatedMessage;
import com.googlecode.kanbanik.client.ModuleDeactivatedMessage;
import com.googlecode.kanbanik.client.Modules;
import com.googlecode.kanbanik.client.messaging.Message;
import com.googlecode.kanbanik.client.messaging.MessageBus;
import com.googlecode.kanbanik.client.messaging.MessageListener;

public class ModulesLyfecycleListenerHandler {
	
	private Class<?> listenOnModule;
	
	private ModulesLifecycleListener listener;
	
	public ModulesLyfecycleListenerHandler(Modules listenOnModule, ModulesLifecycleListener listener) {
		this.listenOnModule = listenOnModule.toClass();
		this.listener = listener;
		
		ActivatedListener activatedListener = new ActivatedListener();
		DeactivatedListener deactivatedListener = new DeactivatedListener(activatedListener);
		MessageBus.registerListener(ModuleActivatedMessage.class, activatedListener);
		MessageBus.registerListener(ModuleDeactivatedMessage.class, deactivatedListener);
	}

	class ActivatedListener implements MessageListener<Class<?>> {

		public void messageArrived(Message<Class<?>> message) {
			if (message.getPayload() == listenOnModule) {
				listener.activated();
			}
		}
		
	}
	
	class DeactivatedListener implements MessageListener<Class<?>> {

		private ActivatedListener activatedListener;
		
		public DeactivatedListener(ActivatedListener activatedListener) {
			this.activatedListener = activatedListener;
		}

		public void messageArrived(Message<Class<?>> message) {
			if (message.getPayload() == listenOnModule) {
				MessageBus.unregisterListener(ModuleActivatedMessage.class, activatedListener);
				MessageBus.unregisterListener(ModuleDeactivatedMessage.class, this);
				listener.deactivated();
			}
		}
		
	}
}
