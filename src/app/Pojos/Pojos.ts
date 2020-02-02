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


class CategoryQuestion {

	  catqId: number;
	  catId: number;
	  catQuestion: number;

}



class Contact {

	  phoneNo: string;
	  street: string;
	  city: string;
	  pincode: number;


}


class Documents {

	  sellerId: number;

	  panNo: string;

	  gstInNo: string;

	  panImageType: string;

	  gstInImageType: string;

	  panImage: any;

	  gstInImage: any;
}


class LoginData {

	  username: string;
	  password: string;

}


class Product {

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


class ProductImage {

	  imageId: number;
	  productId: number;
	  imageType: string;
	  productImageData: any;

}

