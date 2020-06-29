<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="confirmModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <h4>请确认是否删除下列角色：</h4>
                <div id="roleNameDiv" style="text-align: center"></div>
            </div>

            <div class="modal-footer">
                <button id="removeRoleBtn" type="button" class="btn btn-primary">确认删除</button>
                <%--<button id="saveRoleBtn" type="button" class="btn btn-primary">取消</button>--%>


            </div>
        </div>
    </div><!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div><!-- /.modal -->