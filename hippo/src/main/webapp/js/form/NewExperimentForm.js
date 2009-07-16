
Ext.namespace('Hippo.form');

Hippo.form.NewExperimentForm = Ext.extend(Ext.form.FormPanel, {
	createButtonActive: function(tf) {
		this.buttons[0].disabled = (!tf);
	}
	,initComponent : function() {
		Ext.apply(this, {
			region: 'center'
			,id: 'newexpmntform'
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
					'click': {
						fn: function() {
							this.ownerCt.ownerCt.ownerCt.destroy();
						}
					}
				}
			}]
			,defaults: {
				allowBlank: false
				,enableKeyEvents: true
				,width: 350
			}
			,items: [{
				id: 'title'
				,name: 'title'
				,fieldLabel: 'title'
				,maxLength: 64
				,xtype: 'textfield'
				,listeners: {'keyup': {
						fn: function(e, t) {
							this.fireEvent('newexpformchange');
						}
						,scope: this
					}
				}
			}
			,{
				id: 'purpose'
				,name: 'purpose'
				,fieldLabel: 'purpose'
				,maxLength: 128
				,xtype: 'textarea'
				,listeners: {'keyup': {
						fn: function(e, t) {
							this.fireEvent('newexpformchange');}
						,scope: this
					}
				}
			}]
		});		
		Hippo.form.NewExperimentForm.superclass.initComponent.apply(this, arguments);
		this.addEvents('newexpformchange');
	}
});

Ext.reg('newexperimentform', Hippo.form.NewExperimentForm);