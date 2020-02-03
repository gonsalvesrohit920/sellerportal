export class Category {
    catId: number;
    catName: string;
}


export class CategoryAnswer {


	  productId: number;
	  catqId: number;
	  catId: number;
	  catAnswer: string;

}


export class CategoryQuestion {

	  catqId: number;
	  catId: number;
	  catQuestion: number;

}



export class Contact {

	  phoneNo: string;
	  street: string;
	  city: string;
	  pincode: number;


}


export class Documents {

	  sellerId: number;

	  panNo: string;

	  gstInNo: string;

	  panImageType: string;

	  gstInImageType: string;

	  panImage: any;

	  gstInImage: any;
}


export class LoginData {

	  username: string;
	  password: string;

}


export class Product {

    productId: number;
	sellerId: number;
	category: number;
	quantity: number;
	price: number;
	name: string;
	decription: string;
	images: ProductImage[];
    attributes: CategoryAnswer[];

}


export class ProductImage {

	  imageId: number;
	  productId: number;
	  imageType: string;
	  productImageData: any;

}

export class Seller {
	seller_id: number;
	name: string ;
	email: string ;
	password: string ;
	contact: Contact ;
	street: string ;
	city: string ;
	pincode: number ;
	phoneNo: string ;
	documents: Documents;
	valid: boolean;
	exists: boolean;
	applicationStatus: String;
}
