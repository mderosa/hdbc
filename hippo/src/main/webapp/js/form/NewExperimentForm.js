
Ext.namespace('Hippo.form');

Hippo.form.NewExperimentForm = Ext.extend(Ext.form.FormPanel, {

	submitHandler: function(e, t) {
		this.getForm().submit({
			url: 'console/experiments'
			,method: 'post'
			,timeout: 5000
			,params: {
				title: this.getComponent('title').getValue()
				,purpose: this.getComponent('purpose').getValue()
			}
			,success: function(frm, act) {this.ownerCt.destroy()}
		});
	}
	,initComponent : function() {
		Ext.apply(this, {
			id: 'newexpmntform'
			,bodyStyle: 'padding: 5px 5px 5px 5px'
			,frame: true
			,method: 'post'
			,buttons: [{id: 'create' ,text: 'create' ,xtype: 'button'
				,listeners: {
					'click': {
						fn: this.submitHandler
						,scope: this
					}
				}
			}
			,{
				id: 'cancel'
				,text: 'cancel'
				,xtype: 'button'
				,listeners: {
					'click': {
						fn: function(e, t) {
							this.ownerCt.ownerCt.ownerCt.destroy();
						}
					}
				}
			}]
			,defaults: {
				allowBlank: false
				,width: 325
			}
			,items: [
				{id: 'title', name: 'title', fieldLabel: 'title', maxLength: 64, xtype: 'textfield'}
				,{id: 'purpose', name: 'purpose', fieldLabel: 'purpose', maxLength: 128, xtype: 'textarea'}
			]

		});		
		Hippo.form.NewExperimentForm.superclass.initComponent.apply(this, arguments);

	}
});

Ext.reg('newexperimentform', Hippo.form.NewExperimentForm);