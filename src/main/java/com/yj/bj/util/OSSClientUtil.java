package com.yj.bj.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云 OSS文件类
 * 
 * @author YuanDuDu
 */
public class OSSClientUtil {

	private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	private String accessKeyId = "LTAIoPdFWzGoQwVN";
	private String accessKeySecret = "Tv2VwwCiHbDBKF0b2CXzWcorjkGGiP";
	private String bucketName = "image-93236";

	private OSSClient ossClient;

	public OSSClientUtil() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 初始化
	 */
	public void init() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 * 
	 * @param instream
	 *            文件流
	 * @param fileName
	 *            文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(StringUtil
					.getFileType(fileName)));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName,
					fileName, instream, objectMetadata);

			ret = putResult.getETag();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			// log.error(e.getMessage(), e);
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * 从OSS获取文件
	 * 
	 * @param filename
	 *            文件名
	 * @return InputStream 调用方法把流关闭 文件不存在返回null
	 */
	public InputStream downFileFromOSS(String filename) {
		boolean fileExist = ossClient.doesObjectExist(bucketName, filename);
		if (!fileExist)
			return null;
		OSSObject ossObj = ossClient.getObject(bucketName, filename);
		return ossObj.getObjectContent();
	}

	/**
	 * 根据文件名删除OSS服务器上的文件
	 * 
	 * @param filename
	 * @return
	 */
	public String deleteFile(String filename) {
		boolean fileExist = ossClient.doesObjectExist(bucketName, filename);
		if (fileExist) {
			ossClient.deleteObject(bucketName, filename);
			return "delok";
		} else
			return filename + " not found";
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 * 
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {

		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg")
				|| FilenameExtension.equalsIgnoreCase("jpg")
				|| FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx")
				|| FilenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx")
				|| FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		if (FilenameExtension.equalsIgnoreCase("pdf")) {
			return "application/pdf";
		}
		return "image/jpeg";
	}

	/**
	 * 生成一个有生命周期的get方法的URL
	 * 
	 * @param key
	 * @return
	 */
	public URL createUrl(String key, Date expire) {

		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expire);
		return url;
	}

	public String uploadFile2OSS(InputStream instream, String path,
			String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(StringUtil
					.getFileType(fileName)));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, path
					+ "/" + fileName, instream, objectMetadata);
			System.out.println("id");
			ret = putResult.getETag();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			// log.error(e.getMessage(), e);
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static void main(String[] args) throws FileNotFoundException {

	}
}
