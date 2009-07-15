
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperiment = Ext.extend(Ext.Window, {
	
	constructor: function(config) {
		Ext.apply(this, {
			id: 'wndnewexpmnt'
			,title: 'New Experiment'
			//,autoWidth: true
			,autoHeight: true
			,width: 500
			,items: [{
				id: 'frmnewexpmnt'
				,xtype: 'form'
				,defaults: {
					allowBlank: false
					,labelStyle: {padding: '5px'}
					,style: {padding: '5px'}
					,width: 350
				}
				,items: [{
					id: 'title'
					,name: 'title'
					,fieldLabel: 'title'
					,maxLength: 64
					,xtype: 'textfield'
				}
				,{
					id: 'purpose'
					,name: 'purpose'
					,fieldLabel: 'purpose'
					,maxLength: 128
					,xtype: 'textarea'
				}]
				,buttons: [{
					id: 'create'
					,text: 'create'
					,disabled: true
					,xtype: 'button'
				}
				,{
					id: 'cancel'
					,text: 'cancel'
					,xtype: 'button'
					,listeners: {
						'click' : {
							fn: function() {this.destroy();}
							,scope: this
						}
					}
				}]
			}]
		});
		Ext.Window.superclass.constructor.apply(this, arguments);
	}
});