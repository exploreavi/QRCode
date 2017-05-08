package qrcode.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qrc", schema="public")
public class QRCode {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="qr_id", nullable=false, unique=true)
	long qrId;

	@Column(name="code")
	byte[] image;

	public QRCode() {
	}

	public QRCode(byte[] image) {
		this.image = image;
	}

	public long getQrId() {
		return qrId;
	}

	public byte[] getQrCode() {
		return image;
	}
}
