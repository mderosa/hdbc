
Ext.namespace('Hippo.form');

Hippo.form.NewExperimentForm = Ext.extend(Ext.form.FormPanel, {

	submitHandler: function(e, t) {}
	,initComponent : function() {
		Ext.apply(this, {
			region: 'center'
			,id: 'newexpmntform'
			,buttons: [{
				id: 'create'
				,text: 'create'
				,xtype: 'button'
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
				,labelStyle: 'margin-left:5px'
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
		});		
		Hippo.form.NewExperimentForm.superclass.initComponent.apply(this, arguments);

	}
});

Ext.reg('newexperimentform', Hippo.form.NewExperimentForm);