from api2pdf import Api2Pdf

def convert():
    a2p_client = Api2Pdf('YOUR_API_KEY')
    url_to_ppt = 'https://www.api2pdf.com/wp-content/themes/api2pdf/assets/samples/sample-power-point.pptx'
    api_response = a2p_client.LibreOffice.convert_from_url(url_to_ppt)
    return api_response