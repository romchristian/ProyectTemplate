<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>test</title>
        <link rel="stylesheet" type="text/css" href="/ProyectTemplate/plupload2/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
        <script type="text/javascript" src="/ProyectTemplate/js/jquery-1.6.2.min.js" ></script>
        <script type="text/javascript" src="/ProyectTemplate/plupload2/plupload.full.js"></script>
        <script type="text/javascript" src="/ProyectTemplate/plupload2/jquery.plupload.queue/jquery.plupload.queue.js"></script>
        <script type="text/javascript" src="/ProyectTemplate/plupload2/i18n/cn.js"></script>
        <script type="text/javascript">
            /* Convert divs to queue widgets when the DOM is ready */
            $(function () {
                function plupload() {
                    $("#uploader").pluploadQueue({
                        // General settings
                        runtimes: 'html5,flash,silverlight,html4',
                        //runtimes: 'html5,html4',
                        url: 'servlet/fileUpload',
                        max_file_size: '40mb',
                        unique_names: true,
                        chunk_size: '2mb',
                        views: {
                            list: true,
                            thumbs: true, // Show thumbs
                            active: 'thumbs'
                        },
                        // Specify what files to browse for
                        filters: [
                            {title: "Image files", extensions: "jpg,gif,png,jpeg"},
                            {title: "Zip files", extensions: "zip"}
                        ],
                        // Rename files by clicking on their titles
                        rename: true,
                        // Sort files
                        sortable: true,
                        // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
                        dragdrop: true,
                        resize: {width: 800, height: 600, quality: 90, crop: false},
                        // Flash settings
                        flash_swf_url: 'plupload2/plupload.flash.swf',
                        // Silverlight settings
                        silverlight_xap_url: 'plupload2/plupload.silverlight.xap',
                        // Flash settings
                        //flash_swf_url: '/plupload/js/Moxie.swf',
                        // Silverlight settings
                        //silverlight_xap_url: '/plupload/js/Moxie.xap',
                        multipart_params: {'user': 'dujunzhi', 'time': '2012-06-12'}
                    });
                }
                plupload();
                $('#clear').click(function () {
                    plupload();
                });
            });
        </script>

    </head>

    <body style="width: 100%;padding: 0;margin: 0;">
        <div style="width: 100%;padding: 0;margin: 0;">
            <div style="width: 100%;padding: 0;margin: 0;">
                <form id="formId" action="Submit.action" method="post" enctype="multipart/form-data" style="width: 100%;padding: 0;margin: 0;">
                    <div id="uploader" style="width: 100%;padding: 0;margin: 0;">
                        <p> Flash, Silverlight, Gears, BrowserPlus  HTML5 .</p>
                    </div>
                    <input type="button" value="Limpiar" id="clear"/>
                </form>
            </div>
        </div>
    </body>

</html>