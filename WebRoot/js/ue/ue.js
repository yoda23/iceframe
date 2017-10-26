$(function() {
	setUploadAction();
	var ue1 = UE.getEditor('container');
});

function setUploadAction() {
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
		if (action == 'uploadimage' || action == 'uploadvideo') {
			return ctx + '/upload/uploadUEFile';
		} else {
			return this._bkGetActionUrl.call(this, action);
		}
	}
}